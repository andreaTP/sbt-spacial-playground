import sbt._
import Keys._

import java.io.File

object SpacialPlugin extends AutoPlugin {

  object autoImport {
    implicit class TestProjectTC(p: Project) {
      def testProject: Project = {
        p.settings(
          inConfig(Compile)(Defaults.testTasks)
        )
      }
    }
  }

  import autoImport._
  override def trigger = allRequirements

  def removeMain(file: File): File = {
    val replace = "src/main"
    val path = file.getAbsolutePath
    val index = path.lastIndexOfSlice(replace)
    if (index > 0) {
      val newPath = path.patch(path.lastIndexOfSlice(replace), "src", replace.size)
      new java.io.File(newPath)
    } else file
  }

  def removeMain(files: Seq[File]): Seq[File] = {
    files.map(removeMain)
  }

  override lazy val projectSettings = Seq(
    Compile / scalaSource ~= (removeMain),
    Compile / sourceDirectory ~= (removeMain),
    Compile / sourceDirectories ~= (removeMain),
    Compile / resourceDirectory ~= (removeMain),
    Compile / resourceDirectories ~= (removeMain)
  )
}
