def call(script){
    dir(script.env.APP_DIR){
        sh 'npm instal'
        sh 'npm run build'
    }
}