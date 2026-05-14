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

                    scp target/*.jar ubuntu@3.144.138.183:/tmp/app.jar

                    echo "Restarting backend service..."

                    ssh ubuntu@3.144.138.183 << EOF

                    sudo mv /tmp/app.jar /opt/quantity-app/demo-0.0.1-SNAPSHOT.jar

                    sudo systemctl restart quantityapp

                    sudo systemctl status quantityapp --no-pager

                    EOF
                '''
            }
        }

    }
}
