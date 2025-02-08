timeout(time: 60, unit: 'MINUTES') {
    node('gradle') {

        def config = readYaml text: params.YAML_CONFIG
        config.each { k, v -> env."${k}" = v
        }

        stage('Checkout') {
            checkout scm
        }

        stage('Run UI tests') {
            status = sh script: "gradle test -DBASE_URL=$env.BASE_URL", returnStatus: true

            if (status > 0) {
                currentBuild.result = 'UNSTABLE'
            }
        }

        stage('Publish allure report') {
            allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']], reportBuildPolicy: 'ALWAYS'
        }
    }
}
