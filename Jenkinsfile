pipeline {
    agent any

    options {
        skipStagesAfterUnstable()
    }

    /*environment {
        M2_HOME = '/Users/weimao/apache-maven-3.6.1'
        PATH    = "${PATH}:${M2_HOME}/bin:/usr/local/bin/docker"
    }*/

    stages {
        /*stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }*/

        stage('Packaging') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }

        stage('Unit Testing') {
            steps {
                sh 'mvn test'
            }

            /*post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }*/
        }

        stage('Build Image') {
            steps {
                sh "docker build -t payment-service ."
            }
        }

        stage('Deploy') {

            steps {
                //sh "docker run -d --rm --name auth-service-${BUILD_NUMBER} -p 8000:8000 authorization-service:latest"
                script {
                    try {
                        sh "kubectl delete deployment payment-svc-deployment"
                    } catch (err) {
                        echo err.getMessage()
                    }
                }

                script {
                    try {
                        sh "kubectl delete service payment-svc-service"
                    } catch (err) {
                        echo err.getMessage()
                    }
                }

                sh "kubectl apply -f k8s-deployment.yml"
            }
        }
    }
}