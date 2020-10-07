def repoUrl = 'https://github.com/devonfw/mrchecker.git'

class ConfigFile{
    def id;
    def name;
    def comment;
    def location;
}
configFiles = []
configFiles << new ConfigFile(id:'mrchecker-Dispatcher',name:'mrchecker-Dispatcher',comment:'script for providing default fallback if no pipelinescript found in repo',location:'CICD/Dispatcher.groovy')
configFiles << new ConfigFile(id:'mrchecker-Default',name:'mrchecker-Default',comment:'default script for BUILD stage',location:'CICD/Jenkinsfile')

script = ""
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
//add config files to jenkins