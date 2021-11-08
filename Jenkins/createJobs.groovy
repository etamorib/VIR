#!/usr/bin/env groovy

pipelineJob('VIR-complete-build') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url 'https://github.com/etamorib/VIR.git'
                    }
                    branch 'master'
                }
            }
        }
    }
}