def call(script){
    dir(script.env.APP_DIR){
        sh 'npm install'
        sh 'npm run build'
    }
}