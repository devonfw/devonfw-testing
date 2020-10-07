try{
    node('master'){
        checkout scm
        load 'CICD/Jenkinsfile'
    }
} catch (Exception e){
        ansiColor{
            print (\'\'\'
            This is only default script. That means you're branch has no correct            
            jenkins file for this job. If any customization is needed fix this.             
            \'\'\')
        }
} finally {
    node('master'){
        configFileProvider([configFile(fileId: 'mrchecker-Default', variable: 'buildDefault')]) {
            load buildDefault
        }
    }
}

//https://github.com/jenkinsci/pipeline-multibranch-defaults-plugin/blob/master/README.md