pipeline {
  agent any

  environment {
      IMAGE_TAG = "v1.0.0"
      ACCOUNT_ID = "847759515844"
      REGION = "us-east-1"
      ECR_REPO_NAME = "admin-service"
      CLUSTER_NAME = "MyCluster"
      ECS_SERVICE_NAME = "admin-service"
    }

  stages {

    stage('Build') {
      steps {
        // Build the project with Gradle
        sh './gradlew build'
      }
    }

    stage('Test') {
      steps {
        // Run unit tests with Gradle
        sh './gradlew test'
      }
    }

    stage('Dockerize') {
      steps {
      	sh "docker build -t ${ACCOUNT_ID}.dkr.ecr.${REGION}.amazonaws.com/${ECR_REPO_NAME}:${IMAGE_TAG} ."
        sh "docker build -t ${ACCOUNT_ID}.dkr.ecr.${REGION}.amazonaws.com/${ECR_REPO_NAME}:latest ."
      }
    }

    stage('Push to ECR') {
            steps {
                withAWS(credentials: 'aws-access-key-id', region: "${REGION}") {
                 sh "aws ecr get-login-password --region $REGION | docker login --username AWS --password-stdin ${ACCOUNT_ID}.dkr.ecr.${REGION}.amazonaws.com"
                 sh "docker push ${ACCOUNT_ID}.dkr.ecr.${REGION}.amazonaws.com/${ECR_REPO_NAME}:${IMAGE_TAG}"
                 sh "docker push ${ACCOUNT_ID}.dkr.ecr.${REGION}.amazonaws.com/${ECR_REPO_NAME}:latest"
                }
            }
      }

      stage('Update ECS Service') {
            steps {
                withAWS(credentials: 'aws-access-key-id', region: "${REGION}") {
                 sh "aws ecs update-service --cluster ${CLUSTER_NAME} --service ${ECS_SERVICE_NAME} --force-new-deployment"
                }
            }
      }
  }
}