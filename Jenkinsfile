pipeline {
    agent any

    environment {
        IMAGE_NAME = "quantity-backend"
        ECR_REPO = "193281220099.dkr.ecr.us-east-2.amazonaws.com/springboot-app"
        AWS_REGION = "us-east-2"
    }

    stages {

        stage('Build Jar') {
            steps {
                sh '''
                    set -e

                    mvn clean package -DskipTests
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                    set -e

                    docker build -t $IMAGE_NAME .
                '''
            }
        }

        stage('Login To ECR') {
            steps {
                sh '''
                    aws ecr get-login-password --region $AWS_REGION | \
                    docker login \
                    --username AWS \
                    --password-stdin 193281220099.dkr.ecr.us-east-2.amazonaws.com
                '''
            }
        }

        stage('Tag Docker Image') {
            steps {
                sh '''
                    docker tag $IMAGE_NAME:latest $ECR_REPO:latest
                '''
            }
        }

        stage('Push Docker Image') {
            steps {
                sh '''
                    docker push $ECR_REPO:latest
                '''
            }
        }

        stage('Deploy To Server') {
            steps {
                sh '''
                    ssh ubuntu@3.145.58.208 << EOF

                    aws ecr get-login-password --region us-east-2 | \
                    docker login \
                    --username AWS \
                    --password-stdin 193281220099.dkr.ecr.us-east-2.amazonaws.com

                    cd ~/QuantityMeasurement

                    docker compose pull

                    docker compose up -d

EOF
                '''
            }
        }

    }
}
