repoUrl = 'https://github.com/devonfw/mrchecker.git'

class ConfigFile{
    def id;
    def name;
    def comment;
    def location;
}

def makeConfigFiles(cfgFiles){
    def script = ""
    node('master'){
        checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: '*/feature/new_CICD_process']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: repoUrl]]]
        cfgFiles.each{f->
            def content = readFile(file:f.location,encoding:'UTF-8')
            script+=$/
                    configFiles{groovyScript{
                        id('${f.id}')
                        name('${f.name}')
                        comment('${f.comment}')
                        content('''${content}''')
                    }}/$
        }

    }
    return script
}

configFiles = []
configFiles << new ConfigFile(id:'mrchecker-Dispatcher',name:'mrchecker-Dispatcher',comment:'script for providing default fallback if no pipelinescript found in repo',location:'CICD/Dispatcher.groovy')
configFiles << new ConfigFile(id:'mrchecker-Default',name:'mrchecker-Default',comment:'default script for BUILD stage',location:'CICD/Jenkinsfile')

script = ""
script += makeConfigFiles(configFiles)
script += """
multibranchPipelineJob("examples"){
        description("Build source code and provide packages")
        branchSources{
            branchSource{
                source{
                    git{
                        id('12314')
                        remote('${repoUrl}')
                        traits{
                            gitBranchDiscovery()
                        }
                    }
                }
                strategy{
                    defaultBranchPropertyStrategy {
                        props{
                            noTriggerBranchProperty()
                        }
                    }
                }
            }
        }
        orphanedItemStrategy{
            discardOldItems{
                daysToKeep(30)
            }
        }
        factory{
            pipelineBranchDefaultsProjectFactory {
                scriptId('mrchecker-Dispatcher')
                useSandbox(false)
            }
        }
        triggers{
            cron("")
        }
    }
"""
node{
    jobDsl scriptText: script
}