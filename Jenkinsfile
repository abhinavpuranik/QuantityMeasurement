pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                sh '''
                    set -e

                    echo "Java Version:"
                    java -version

                    echo "Maven Version:"
                    mvn -version

                    echo "Building application..."

                    mvn clean package -DskipTests
                '''
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    set -e

                    echo "Copying jar to backend server..."

                    scp target/demo-0.0.1-SNAPSHOT.jar ubuntu@172.31.47.181:/tmp/app.jar

                    echo "Deploying application..."

                    ssh ubuntu@172.31.47.181 "sudo mv /tmp/app.jar /opt/quantity-app/demo-0.0.1-SNAPSHOT.jar && sudo systemctl restart quantityapp && sudo systemctl status quantityapp --no-pager"
                '''
            }
        }

    }
}
