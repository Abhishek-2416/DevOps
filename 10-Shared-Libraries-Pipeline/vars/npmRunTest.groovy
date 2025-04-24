def call(String APP_DIR = '10-Shared-Libraries-Pipeline'){
    dir(APP_DIR){
        sh 'npm run test'
    }
}