FROM jenkins/jenkins:lts-jdk11

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
USER root
RUN apt-get update && apt-get install -y maven

COPY seed-job.xml /usr/share/jenkins/ref/jobs/seed-job/config.xml
USER jenkins
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false