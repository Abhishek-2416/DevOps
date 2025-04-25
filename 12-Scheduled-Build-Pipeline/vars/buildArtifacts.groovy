def call(script) {
    dir(APP_DIR){
        archiveArtifacts artifacts: "dist/**" , fingerprint: true
    }
}