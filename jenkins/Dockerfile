FROM jenkins/jenkins:2.580

USER root

RUN apt-get update \
    && apt-get install -y openjdk-25-jdk \
    && rm -rf /var/lib/apt/lists/*

USER jenkins