package com.hbm.animloader;

import javax.annotation.Nullable;

public class AnimationWrapper {

	public static final AnimationWrapper EMPTY = new AnimationWrapper(Animation.EMPTY){
		public AnimationWrapper onEnd(final EndResult res) {
			return this;
		}
    };
	
	public Animation anim;
	
	public long startTime;
	public float speedScale = 1;
	public boolean reverse;
	public EndResult endResult = EndResult.END;
	public int prevFrame = 0;
	
	public AnimationWrapper(final Animation a){
		this.anim = a;
		this.startTime = System.currentTimeMillis();
	}
	
	public AnimationWrapper(final long startTime, final Animation a){
		this.anim = a;
		this.startTime = startTime;
	}
	
	public AnimationWrapper(final long startTime, final float scale, final Animation a){
		this.anim = a;
		this.speedScale = scale;
		this.startTime = startTime;
	}
	
	public AnimationWrapper onEnd(final EndResult res){
		this.endResult = res;
		return this;
	}
	
	public AnimationWrapper reverse(){
		this.reverse = !reverse;
		return this;
	}
	
	public AnimationWrapper cloneStats(final AnimationWrapper other){
		this.anim = other.anim;
		this.startTime = other.startTime;
		this.reverse = other.reverse;
		this.endResult = other.endResult;
		return this;
	}
	
	public AnimationWrapper cloneStatsWithoutTime(final AnimationWrapper other){
		this.anim = other.anim;
		this.reverse = other.reverse;
		this.endResult = other.endResult;
		return this;
	}
	
	public enum EndType {
		END,
		REPEAT,
		REPEAT_REVERSE,
		START_NEW,
		STAY
    }
	
	public static class EndResult {
		
		public static final EndResult END = new EndResult(EndType.END, null);
		
		EndType type;
		AnimationWrapper next;
		
		public EndResult(final EndType type) {
			this(type, null);
		}
		
		public EndResult(final EndType type, @Nullable final AnimationWrapper next) {
			this.type = type;
			this.next = next;
		}
		
	}
}
