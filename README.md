## This microservice is designed to be run on AWS infrastructure, specifically using ECS Fargate, Service Discovery, and a CI/CD pipeline with Jenkins.

This is a basic Spring Boot microservice created to test AWS infrastructure with services like ECS Fargate, Service Discovery with service connect, and CI/CD pipeline with Jenkins. This project is a good starting point for learning how to work with Spring Boot microservices in AWS.

![Alt text](images/arch.png?raw=true "Optional Title")

## The full CDK project for provisioning this infrastructure can be found here.
### [CDK Project](https://github.com/peaqock-financials/cdk-fargate-alb-project.git)



## CI/CD Pipeline with Jenkins

This microservice is built and deployed using a CI/CD pipeline with Jenkins. Jenkins is a popular open-source automation server that can be used to automate various stages of the software delivery process. The pipeline includes stages for building, testing, and deploying the microservice.

![Alt text](images/pipe.png?raw=true "Optional Title")

## Pipeline Overview

This pipeline includes the following stages:

* **Checkout**: This stage checks out the code from the Git repository.
* **Build**: This stage builds the Spring Boot microservice using Gradle.
* **Test**: This stage runs automated tests on the microservice.
* **Dockerize**: This stage creates Docker images for the microservice with custom version tag and latest tag.(technique for preventing tags overwrite)
* **Push to ECR**: This stage pushes the Docker images to the Amazon Elastic Container Registry (ECR).
* **Update ECS Service**: This stage updates the ECS service with a new deployment to the latest version of the Docker image.

## Environment Variables

The pipeline uses the following environment variables:

* **IMAGE_TAG**: The version tag for the Docker image.
* **ACCOUNT_ID**:The AWS account ID.
* **REGION**: The AWS region where the ECR repository and ECS cluster are located.
* **ECR_REPO_NAME**: The name of the ECR repository where the Docker image will be stored.
* **CLUSTER_NAME**: The name of the ECS cluster where the microservice will be deployed.
* **ECS_SERVICE_NAME**: The name of the ECS service where the microservice will be deployed.

## Jenkins Configuration

To use this pipeline, you must configure the following in Jenkins:

* Configure AWS credentials for the Jenkins user with permission to access ECR and ECS services.
* Create a Jenkins pipeline project and configure the Git repository with the Jenkinsfile.
* Set the environment variables in the pipeline configuration to match your AWS environment.
* Run the pipeline to build, test, and deploy the Spring Boot microservice to ECS Fargate with a new deployment.
