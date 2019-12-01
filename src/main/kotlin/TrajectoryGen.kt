import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.acmerobotics.roadrunner.path.heading.*
import com.acmerobotics.roadrunner.trajectory.Trajectory
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder
import com.acmerobotics.roadrunner.trajectory.constraints.DriveConstraints

object TrajectoryGen {
    private val constraints = DriveConstraints(45.0, 60.0, 0.0, 270.0.toRadians, 270.0.toRadians, 0.0)
    private val startPose = Pose2d(48.0, -28.0, 170.0.toRadians)

    fun createTrajectory(): ArrayList<Trajectory> {
        val list = ArrayList<Trajectory>()

        val builder1 = TrajectoryBuilder(startPose, constraints)

        // dump routine
        builder1
            //.lineTo( Vector2d(22.0, -63.0), ConstantInterpolator(90.0.toRadians))
            //.splineTo(Pose2d(Vector2d(0.0, -36.0), 180.0.toRadians), LinearInterpolator(90.0.toRadians, 90.0.toRadians ))
            .lineTo(Vector2d(36.0, -28.0), ConstantInterpolator(90.0.toRadians))

        val builder2 = TrajectoryBuilder(Pose2d(36.0, -28.0, 0.0.toRadians), constraints)

        builder2.lineTo(Vector2d(36.0, -63.0), ConstantInterpolator(0.0.toRadians))

        val builder3 = TrajectoryBuilder(Pose2d(36.0, -63.0, 0.0.toRadians), constraints)

        builder3
            .back(16.0)
            .strafeLeft(28.0)
            .back(20.0)


        // weird reversed profiles
        /* builder
             .setReversed(true)
             .splineTo(Pose2d(-12.0, -42.0, 180.0.toRadians), SplineInterpolator(180.0.toRadians, startPose.heading))// y = 39 is halfway between the skybridge and partner with 6" on either side
             .splineTo(Pose2d(28.0, -42.0, 180.0.toRadians), SplineInterpolator(180.0.toRadians, 180.0.toRadians))
             .splineTo(Pose2d(48.0, -33.0, -90.0.toRadians), SplineInterpolator(180.0.toRadians, -90.0.toRadians))
             .lineTo(Vector2d(48.0, -26.0), ConstantInterpolator(-90.0.toRadians))*/


        list.add(builder1.build())
        list.add(builder2.build())
        list.add(builder3.build())
        return list
    }

    fun drawOffbounds() {
        GraphicsUtil.fillRect(Vector2d(0.0, -63.0), 18.0, 18.0) // robot against the wall
    }
}

val Double.toRadians get() = (Math.toRadians(this))