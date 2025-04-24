// vars/npmInstallAndBuild.groovy
def call(String APP_DIR = '10-Shared-Libraries-Pipeline') {
    dir(APP_DIR) {
        sh 'npm install'
        sh 'npm run build'
    }
}


// def call(String APP_DIR = '10-Shared-Libraries-Pipeline'){
//     dir(APP_DIR){
        
//     }
// }