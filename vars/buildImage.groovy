def call(imageName, tag) {
  sh """
    /kaniko/executor \
      --dockerfile=Dockerfile \
      --context=\$(pwd) \
      --destination=${imageName}:${tag}
  """
}
