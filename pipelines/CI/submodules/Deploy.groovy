def call(){

//# Build deployable app
    stage('Deploy'){
        sh"""
            export BASE_PATH=.
            cd ${env.APP_WORKSPACE}
            mvn -q deploy -DskipTests=true
        """
        archive "${env.APP_WORKSPACE}target/*.jar"
    }
}
return this