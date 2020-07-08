import org.apache.commons.io.FileUtils

import picocli.AutoComplete

String imageName = properties.get("javacli.imageName")
String mainClass = properties.get("javacli.mainClass")

File nativeImage = new File(new File(basedir as File, "target"), imageName)
File installedNativeImage = new File(new File(basedir as File, "dotfiles/bin"), imageName)
installedNativeImage.delete()
FileUtils.copyFile(nativeImage, installedNativeImage)
installedNativeImage.setExecutable(true, false)

File completionFile = new File(basedir as File, String.format("dotfiles/.bash_completion.d/%s-completion.sh", imageName))
completionFile.getParentFile().mkdirs()
AutoComplete.main(
  "-n",
  imageName,
  "-o",
  completionFile.getAbsolutePath(),
  "--force",
  mainClass
)
