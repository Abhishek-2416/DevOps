def call(script) {
    dir(script.env.APP_DIR){
        archiveArtifacts artifacts: "dist/**" , fingerprint: true
    }
}