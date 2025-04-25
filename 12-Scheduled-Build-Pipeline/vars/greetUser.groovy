def call(script){
    def name = script.params.USERNAME
    def env = script.params.DEPLOY_ENV

    echo "Hey ${name}, how are you doing"
    echo "We are pushing the code to the ${env}"
}