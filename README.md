# wayezi_cctvmodule
wayezi projectwork on stop button if the recording will start

### Considerations
>Before enabling your application to use cameras on Android devices, you should consider a few questions about how your app intends to use this hardware feature.

>Camera Requirement - Is the use of a camera so important to your application that you do not want your application installed on a device that does not have a camera? If so, you should declare the camera requirement in your manifest.

>Quick Picture or Customized Camera - How will your application use the camera? 
Are you just interested in snapping a quick picture or video clip, or will your application provide a new way to use cameras? For getting a quick snap or clip, consider Using Existing Camera Apps. For developing a customized camera feature, check out the Building a Camera App section.

>Foreground Services Requirement - When does your app interact with the camera? On Android 9 (API level 28) and later, apps running in the background cannot access the camera. Therefore, you should use the camera either when your app is in the foreground or as part of a foreground service.
Storage - Are the images or videos your application generates intended to be only visible to your application or shared so that other applications such as Gallery or other media and social apps can use them? Do you want the pictures and videos to be available even if your application is uninstalled? Check out the Saving Media Files section to see how to implement these options.

The Android framework supports capturing images and video through the android.hardware.camera2 API or camera Intent. Here are the relevant classes:

### android.hardware.camera2

>This package is the primary API for controlling device cameras. It can be used to take pictures or videos when you are building a camera application.

### Camera
>This class is the older deprecated API for controlling device cameras.
### SurfaceView
>This class is used to present a live camera preview to the user.

### MediaRecorder

>This class is used to record video from the camera.
### Intent

>An intent action type of MediaStore.ACTION_IMAGE_CAPTURE or MediaStore.ACTION_VIDEO_CAPTURE can be used to capture images or videos without directly using the Camera object.
### Manifest declarations
>Before starting development on your application with the Camera API, you should make sure your manifest has the appropriate declarations to allow use of camera hardware and other related features.

### Camera Permission -

>Your application must request permission to use a device camera.



