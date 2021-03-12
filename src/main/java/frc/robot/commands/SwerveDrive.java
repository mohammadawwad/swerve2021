package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drivetrain.SwerveDriveTrain;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SwerveDrive extends CommandBase {

  private SwerveDriveTrain driveTrain;
  private XboxController controller;

  private final SlewRateLimiter xspeedLimiter = new SlewRateLimiter(6);
  private final SlewRateLimiter yspeedLimiter = new SlewRateLimiter(6);
  private final SlewRateLimiter rotLimiter = new SlewRateLimiter(6);
  
  public SwerveDrive(SwerveDriveTrain driveTrain, XboxController controller) {
    this.driveTrain = driveTrain;
    this.controller = controller;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

    double leftY = -xspeedLimiter.calculate(controller.getY(GenericHID.Hand.kLeft)) + Constants.ROBOT.MAX_SPEED.get();
    double leftX = -yspeedLimiter.calculate(controller.getX(GenericHID.Hand.kLeft)) * Constants.ROBOT.MAX_SPEED.get();
    double rightX = -rotLimiter.calculate(controller.getX(GenericHID.Hand.kRight)) * Constants.ROBOT.MAX_SPEED.get();

    driveTrain.drive(leftX, leftY, rightX);
    
    //Displays joystick values on Smart Dashboard
    SmartDashboard.putNumber("Left Y", leftY);
    SmartDashboard.putNumber("Left X", leftX);
    SmartDashboard.putNumber("Right X", rightX);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
