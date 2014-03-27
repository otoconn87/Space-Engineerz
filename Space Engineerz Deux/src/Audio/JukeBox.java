package Audio;


import javax.sound.sampled.*;


public class JukeBox {

	 private Clip clip;
	 private boolean isPlaying;
	 
	    public JukeBox(String s) {
	    	        
	    	
	        try {
                    
                AudioInputStream sound = AudioSystem.getAudioInputStream(getClass()
    					.getResourceAsStream(s));
                
                AudioFormat iFormat = sound.getFormat(); //base format
    			AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, iFormat.getSampleRate(), 16, iFormat.getChannels(),
    					iFormat.getChannels() * 2, iFormat.getSampleRate(), //decode format
    					false);
    			
    			AudioInputStream ais = AudioSystem.getAudioInputStream(format, sound);
                clip = AudioSystem.getClip();
                clip.open(ais);
	            
	            
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            
	        }
	     
	    }
	    public void play(){
	    	if(isPlaying){
	    		return;
	    	}
	    	else{
	        clip.setFramePosition(0);  // Must always rewind!
	        clip.start();
	        isPlaying = true;
	    	}
	    }
	    
	    public void loop(){
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	    }
	    
	    public void stop(){
	          clip.stop();
	          isPlaying = false;
	    }
	    
	    public void close(){
	    	clip.close();
	    }
	    }