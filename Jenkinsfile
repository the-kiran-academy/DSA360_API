pipeline {
    agent any
    
    environment {
        DATE_TIME = new Date().format('yyyyMMddHHmmss')
        DOCKER_IMAGE_NAME = "ram2715/dsa360-${DATE_TIME}"
        DOCKERFILE_PATH = 'Dockerfile'
        DOCKERHUB_CREDENTIALS = credentials('dockerpassword')
        PREVIOUS_IMAGE = ''
    }

    tools {
        maven "MAVEN_HOME"
    }

    stages {
        stage('Get Code') {
            steps {
                echo "Cloning the repository..."
                git 'https://github.com/the-kiran-academy/DSA360_API.git'
            }
        }

        stage('Build') {
            steps {
                echo "Building the Maven project..."
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo "Running SonarQube analysis..."
                withSonarQubeEnv('MySonarQubeServer') {
                    bat "mvn sonar:sonar"
                }
            }
        }

        stage('Find Previous Docker Image') {
            steps {
                script {
                    echo "Searching for previous Docker image..."
                    
                    def existingImages = bat(script: 'docker images --format "{{.Repository}}:{{.Tag}}" | findstr "ram2715/dsa360-"', returnStdout: true).trim()
                    
                    if (existingImages) {
                        def lastImage = existingImages.split('\n')[0]  // Take the first matched image
                        PREVIOUS_IMAGE = lastImage.trim()
                        echo "Found previous image: ${PREVIOUS_IMAGE}"
                    } else {
                        echo "No previous image found."
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    echo "Building new Docker image..."
                    def dockerImage = docker.build(env.DOCKER_IMAGE_NAME, "--cache-from ${env.DOCKER_IMAGE_NAME} -f ${env.DOCKERFILE_PATH} .")
                    echo "Docker image built: ${env.DOCKER_IMAGE_NAME}"
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    echo "Pushing Docker image to DockerHub..."
                    withCredentials([string(credentialsId: 'dockertoken', variable: 'dockertoken')]) {
                        bat "docker login -u ram2715 -p ${dockertoken}"
                        bat "docker push ${env.DOCKER_IMAGE_NAME}"
                        echo "Docker image pushed successfully!"
                    }
                }
            }
        }

        stage('Cleanup Previous Docker Image') {
            when {
                expression { PREVIOUS_IMAGE != '' }
            }
            steps {
                script {
                    echo "Removing previous Docker image: ${PREVIOUS_IMAGE}"
                    bat "docker rmi ${PREVIOUS_IMAGE} || echo 'Failed to delete previous image or not found.'"
                }
            }
        }

        stage('Cleanup Workspace') {
            steps {
                echo "Cleaning up unnecessary files..."
                bat "mvn clean"
                bat "docker system prune -f"
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline succeeded! Docker image pushed and cleaned up successfully.'
        }
        failure {
            echo '❌ Pipeline failed! Check logs for details.'
        }
    }
}
