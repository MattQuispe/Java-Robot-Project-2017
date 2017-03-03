package org.usfirst.frc.team303.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {
	
	ArrayList<Action> arr= new ArrayList<Action>();
	int taskNum=0;
	
	public Autonomous() {
		
	}
	
	enum AutoStates {
		Default, RightPeg, LeftPeg, MidPeg, rBoiler, bBoiler, rHopper;
	}
	
	public void run(){
		if(arr.size()>=taskNum){
			arr.get(taskNum).run();
			if(arr.get(taskNum).isFinished()) {
				taskNum++;
			}
			//arr.get(taskNum).run();
		}
		
		SmartDashboard.putNumber("taskNum", taskNum);
	}
	
	public void assembleGearFromRBoiler() {
		//arr.add(makeSimpleParallelAction(new ActionWait(4), new ActionShooter(true, 23000)));
		//arr.add(new ActionShooter(false, 0));
		arr.add(new ActionZero());
		arr.add(new ActionDriveStraightByEncoders(-3400));
		arr.add(new ActionTurnToAngle(-190, true, 3, true, 0.3, false ));
		scoreGearBoiler();
	}
	
	public void assembleGearFromBBoiler() { // ------------------------------------------------------------------------------
		//arr.add(makeSimpleParallelAction(new ActionWait(4), new ActionShooter(true, 29000)));
		//arr.add(new ActionShooter(false, 0));
		//arr.add(new ActionDriveStraightByEncoders(500));
		//arr.add(new ActionTurnToAngle(85, true, 3, true, 0.005, true));
		//arr.add(new ActionDriveStraightByEncoders(6000));
		//arr.add(new ActionTurnToAngle(65, true, 3, true, 1, true));
		//arr.add(makeSimpleParallelAction(new ActionWait(4), new ActionShooter(true, 23000)));
		//arr.add(new ActionShooter(false, 0));
		arr.add(new ActionTurnAngleUntilCollision(181, true, 18, true, 0, true, 1.9));
		arr.add(new ActionTurnAngleUntilCollision(-34, true, 6, true, 0.4, true, 1));
		scoreGearBoiler();
	}
	
	public void assembleGearFromLeftPeg() {
		arr.add(new ActionDriveStraightByEncoders(9600)); 
		arr.add(new ActionTurnToAngle(61, true, 3)); 
		scoreGear();
	}
	
	public void assembleGearFromMidPeg() {
		scoreGear();
	}
	
	public void assembleGearFromRightPeg() {
		arr.add(new ActionDriveStraightByEncoders(9600)); 
		arr.add(new ActionTurnToAngle(-61, true, 3)); 
		scoreGear();
	}
	
	public void scoreGearBoiler() {
		arr.add(new ActionNacRac(false));
		arr.add(new ActionDriveToGoalByArea(13500));
		arr.add(makeSimpleParallelAction(new ActionDrive(), new ActionWait(1)));
		arr.add(makeSimpleParallelAction(new ActionWait(0.75), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-1000), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-6000), new ActionNacRac(false)));

	}
	
	public void scoreGear() {
		arr.add(new ActionNacRac(false));
		arr.add(new ActionDriveToGoalByArea(13500));
		arr.add(makeSimpleParallelAction(new ActionDrive(), new ActionWait(1)));
		arr.add(makeSimpleParallelAction(new ActionWait(0.75), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-1000), new ActionNacRac(true)));
		arr.add(makeSimpleParallelAction(new ActionDriveStraightByEncoders(-6000), new ActionNacRac(false)));
		
	}
	
public void assembleHopperFromRedAllianceStation() {
		arr.add(new ActionDriveStraightByEncoders(10600)); 
		arr.add(new ActionTurnToAngle(-90, true, 3));
		arr.add(new ActionDriveStraightByCollision(-.6));
		arr.add(makeSimpleParallelAction(new ActionWait(1), new ActionIntake(1)));
		arr.add(new ActionDriveStraightByEncoders(1000));
		arr.add(new ActionTurnToAngle(-95, true, 3));
		arr.add(makeSimpleParallelAction(new ActionWait(5),new ActionShooter(true, 27000)));
		arr.add(new ActionShooter(false, 0));
	}
	
	
	public ActionParallelAction makeSimpleParallelAction(Action con, Action nonCon) {
		ArrayList<Action> nonConAction = new ArrayList<Action>();
		nonConAction.add(nonCon);
		ArrayList<Action> conAction = new ArrayList<Action>();
		conAction.add(con);
		return new ActionParallelAction(conAction, nonConAction);
		
	}
	
} 
