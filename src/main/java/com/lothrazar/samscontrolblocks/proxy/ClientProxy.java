package com.lothrazar.samscontrolblocks.proxy;

import org.lwjgl.input.Keyboard;   
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class ClientProxy extends CommonProxy 
{   
	public static KeyBinding keyPush;
	public static KeyBinding keyPull; 
	public static KeyBinding keyTransform;  
	public static final String keyPushName = "key.push";
	public static final String keyPullName = "key.pull";
	public static final String keyTransformName = "key.transform";

	public static int keyPush_code;
	public static int keyPull_code; 
	public static int keyTransform_code;  
	
	public static final String keyCategoryBlocks = "key.categories.blocks";
	
    @Override
    public void registerRenderers() 
    {  
    	registerKeyBindings();  
    }
  
	private void registerKeyBindings() 
	{
        keyTransform = new KeyBinding(keyTransformName, Keyboard.KEY_V, keyCategoryBlocks); 
        ClientRegistry.registerKeyBinding(ClientProxy.keyTransform);
        keyPush = new KeyBinding(keyPushName, Keyboard.KEY_G, keyCategoryBlocks); 
        ClientRegistry.registerKeyBinding(ClientProxy.keyPush);
        keyPull = new KeyBinding(keyPullName, Keyboard.KEY_B,  keyCategoryBlocks); 
        ClientRegistry.registerKeyBinding(ClientProxy.keyPull);
        
         //since KeyBinding class does not exist on server: this seems to fix... something
        keyPull_code = keyPull.getKeyCode();
        keyPush_code = keyPush.getKeyCode();
        keyTransform_code = keyTransform.getKeyCode();
	} 
}
