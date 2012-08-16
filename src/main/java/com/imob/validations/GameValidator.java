package com.imob.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;


public class GameValidator implements ConstraintValidator<GameValid, Object>{
	@Override
	public void initialize(final GameValid o) {				
	}
	@Override
	public boolean isValid(final Object game, ConstraintValidatorContext arg1) {
		BeanWrapper bGame = new BeanWrapperImpl(game);
		int winPid1 = ((Number)bGame.getPropertyValue("winPid1")).intValue();
		int winPid2 = ((Number)bGame.getPropertyValue("winPid2")).intValue();
		int losePid1 = ((Number)bGame.getPropertyValue("losePid1")).intValue();
		int losePid2 = ((Number)bGame.getPropertyValue("losePid2")).intValue();
		if (winPid1 >= winPid2 || winPid1 == losePid1 || winPid1 == losePid2 || winPid2 == losePid1 || 
				winPid2 == losePid2 || losePid1 >= losePid2){
			return false;			
		}
		int winScore = ((Number)bGame.getPropertyValue("winScore")).intValue();
		int loseScore = ((Number)bGame.getPropertyValue("loseScore")).intValue();
		if(winScore <= loseScore){
			return false;
		}			
		return true;
	}
}
