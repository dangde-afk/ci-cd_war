pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo "🏗 Building WAR"
                bat 'mvnw clean package -DskipTests'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                echo "🚀 Deploying WAR to Tomcat"
                bat '''
                copy target\\myapp.war C:\\apache-tomcat-9.0.85\\webapps\\myapp.war
                C:\\apache-tomcat-9.0.85\\bin\\shutdown.bat
                timeout 3
                C:\\apache-tomcat-9.0.85\\bin\\startup.bat
                '''
            }
        }
    }
}
