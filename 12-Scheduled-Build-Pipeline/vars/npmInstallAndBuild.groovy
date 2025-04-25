def call(script){
    dir(script.env.APP_DIR){
        if(script.prams.DEPLOY_ENV == 'prod'){
            input message: "Are you sure you want to push it to production", ok: "Yeah, proceed"
        }
        sh 'npm install'
        sh 'npm run build'
    }
}