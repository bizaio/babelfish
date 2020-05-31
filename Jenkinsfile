
node {
	def server
	def buildInfo
	def rtMaven
	
	stage ('Clone Source and switch Branch') {
        git branch: env.BRANCH_NAME, credentialsId: 'bizabuilder', url: 'https://github.com/bizaio/babelfish.git'
    }
	
    stage ('Artifactory configuration') {
        server = Artifactory.server "artifactory"

        rtMaven = Artifactory.newMavenBuild()
        rtMaven.tool = "Maven3"
        rtMaven.deployer releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local', server: server
        rtMaven.resolver releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot', server: server
        rtMaven.deployer.deployArtifacts = false

        buildInfo = Artifactory.newBuildInfo()
    }

    stage ('Test') {
        rtMaven.run goals: 'clean test'
    }
        
    stage ('Install') {
        rtMaven.run goals: 'install', buildInfo: buildInfo
    }
 
    stage ('Deploy') {
        rtMaven.deployer.deployArtifacts buildInfo
    }
        
    stage ('Publish build info') {
        server.publishBuildInfo buildInfo
    }
}