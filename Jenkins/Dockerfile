FROM jenkins/jenkins

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

COPY seed-job.xml /usr/share/jenkins/ref/jobs/seed-job/config.xml

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false