package me.zhang.workbench;

import me.zhang.art.ipc.messenger.MessengerClientActivity;
import me.zhang.art.ipc.parcel.ClientActivity;
import me.zhang.art.ipc.provider.ProviderExternalUserActivity;
import me.zhang.art.ipc.socket.TcpClientActivity;
import me.zhang.workbench.adapter.AdapterActivity;
import me.zhang.workbench.adapter.RecyclerActivity;
import me.zhang.workbench.adapter.viewPagerList.ViewPagerListActivity;
import me.zhang.workbench.animation.AnimateLayoutChangesActivity;
import me.zhang.workbench.animation.AnimationsActivity;
import me.zhang.workbench.animation.CardFlipActivity;
import me.zhang.workbench.animation.LayoutAnimationActivity;
import me.zhang.workbench.animation.LogoWhiteAnimActivity;
import me.zhang.workbench.animation.PropertyAnimationActivity;
import me.zhang.workbench.animation.RecyclerViewAnimationActivity;
import me.zhang.workbench.animation.TranslateActivity;
import me.zhang.workbench.animation.WrapperActivity;
import me.zhang.workbench.base.MenuActivity;
import me.zhang.workbench.basic.BasicUiActivity;
import me.zhang.workbench.binding.DataBindingActivity;
import me.zhang.workbench.configuration.ChangeOrientation;
import me.zhang.workbench.design.BottomSheetActivity;
import me.zhang.workbench.design.CustomFabActivity;
import me.zhang.workbench.design.DynamicSurfacesActivity;
import me.zhang.workbench.design.FabActivity;
import me.zhang.workbench.design.PaletteActivity;
import me.zhang.workbench.design.SimplePaperTransformations;
import me.zhang.workbench.design.animation.AnimateActivity;
import me.zhang.workbench.design.coordinate.CoordinateActivity;
import me.zhang.workbench.design.font.FontActivity;
import me.zhang.workbench.design.image.ImmersiveImagesActivity;
import me.zhang.workbench.design.image.RoundedImagesActivity;
import me.zhang.workbench.design.image.ThreeTwoImageActivity;
import me.zhang.workbench.design.interpolator.InterpolatorActivity;
import me.zhang.workbench.design.transition.GridActivity;
import me.zhang.workbench.design.vector.VectorDrawableActivity;
import me.zhang.workbench.drawable.ClipDrawableActivity;
import me.zhang.workbench.drawable.CustomDrawableActivity;
import me.zhang.workbench.drawable.LevelListActivity;
import me.zhang.workbench.drawable.ScaleDrawableActivity;
import me.zhang.workbench.drawable.TransitionActivity;
import me.zhang.workbench.event.BusActivity;
import me.zhang.workbench.gestures.GesturesActivity;
import me.zhang.workbench.graphics.BitmapScaling;
import me.zhang.workbench.graphics.OpenGLES20Activity;
import me.zhang.workbench.handler.HandlerActivity;
import me.zhang.workbench.hello.HelloWorldActivity;
import me.zhang.workbench.intent.IntentActivity;
import me.zhang.workbench.keyboard.HideKeyboardActivity;
import me.zhang.workbench.launchMode.AlphaActivity;
import me.zhang.workbench.layout.ConstraintActivity;
import me.zhang.workbench.layout.CustomLayoutActivity;
import me.zhang.workbench.layout.GridLayoutActivity;
import me.zhang.workbench.layout.LayoutActivity;
import me.zhang.workbench.layout.SimpleLayoutActivity;
import me.zhang.workbench.leaks.LeakyActivity;
import me.zhang.workbench.library.UseAndroidLibraryActivity;
import me.zhang.workbench.lifecycle.FragmentLifecycleInsideViewPager;
import me.zhang.workbench.lifecycle.LifecycleActivity;
import me.zhang.workbench.listvideo.VideoActivity;
import me.zhang.workbench.memory.MemoryManagement;
import me.zhang.workbench.others.InvokeStarDetailActivity;
import me.zhang.workbench.remoteViews.NotificationActivity;
import me.zhang.workbench.resources.TestGetDimens;
import me.zhang.workbench.retrofit.TestActivity;
import me.zhang.workbench.rx.RxActivity;
import me.zhang.workbench.service.SaveMyLocationActivity;
import me.zhang.workbench.state.RestoreActivityState;
import me.zhang.workbench.stateList.StateListActivity;
import me.zhang.workbench.style.PreviewActivity;
import me.zhang.workbench.thread.LoggerActivity;
import me.zhang.workbench.thread.ThreadActivity;
import me.zhang.workbench.touchEvent.TouchEventActivity;
import me.zhang.workbench.touchEvent.conflict.HVConflictExternalActivity;
import me.zhang.workbench.touchEvent.conflict.HVConflictInternalActivity;
import me.zhang.workbench.ui.CircleViewActivity;
import me.zhang.workbench.view.CustomViewActivity;
import me.zhang.workbench.view.CustomViewGroupActivity;
import me.zhang.workbench.view.GetViewDimensActivity;
import me.zhang.workbench.view.ScrollViewActivity;
import me.zhang.workbench.view.SmoothScrollActivity;
import me.zhang.workbench.view.ViewScrollActivity;
import me.zhang.workbench.view.VisibilityActivity;
import me.zhang.workbench.volley.VolleyTestActivity;
import me.zhang.workbench.webview.WebViewActivity;
import me.zhang.workbench.window.ShowDialog;
import me.zhang.workbench.window.UseWindow;

public class MainActivity extends MenuActivity {

    @Override
    protected void prepareMenu() {
        addMenuItem("Preview Window", PreviewActivity.class);
        addMenuItem("Layout", LayoutActivity.class);
        addMenuItem("Leaks", LeakyActivity.class);
        addMenuItem("WebView", WebViewActivity.class);
        addMenuItem("GesturesActivity", GesturesActivity.class);
        addMenuItem("Visibility", VisibilityActivity.class);
        addMenuItem("Card Flip", CardFlipActivity.class);
        addMenuItem("Animate Layout Changes", AnimateLayoutChangesActivity.class);
        addMenuItem("I/O Logo White Animation", LogoWhiteAnimActivity.class);
        addMenuItem("Client", ClientActivity.class);
        addMenuItem("Messenger Client", MessengerClientActivity.class);
        addMenuItem("Use Provider", ProviderExternalUserActivity.class);
        addMenuItem("Tcp client", TcpClientActivity.class);
        addMenuItem("Simple Layout", SimpleLayoutActivity.class);
        addMenuItem("View Scroll", ViewScrollActivity.class);
        addMenuItem("Translate Animation", TranslateActivity.class);
        addMenuItem("Smooth Scroll", SmoothScrollActivity.class);
        addMenuItem("Touch Event", TouchEventActivity.class);
        addMenuItem("HV Conflict External", HVConflictExternalActivity.class);
        addMenuItem("HV Conflict Internal", HVConflictInternalActivity.class);
        addMenuItem("Get View Dimens", GetViewDimensActivity.class);
        addMenuItem("ScrollView Dimens", ScrollViewActivity.class);
        addMenuItem("Custom View", CustomViewActivity.class);
        addMenuItem("Custom Layout", CustomLayoutActivity.class);
        addMenuItem("Circle View", CircleViewActivity.class);
        addMenuItem("Notification", NotificationActivity.class);
        addMenuItem("Handler", HandlerActivity.class);
        addMenuItem("Bottom Sheet", BottomSheetActivity.class);
        addMenuItem("Level List Drawable", LevelListActivity.class);
        addMenuItem("Transition Drawable", TransitionActivity.class);
        addMenuItem("Retrofit Test", TestActivity.class);
        addMenuItem("Rx", RxActivity.class);
        addMenuItem("Contraint Layout", ConstraintActivity.class);
        addMenuItem("Scale Drawable", ScaleDrawableActivity.class);
        addMenuItem("Clip Drawable", ClipDrawableActivity.class);
        addMenuItem("Custom Drawable", CustomDrawableActivity.class);
        addMenuItem("Animations", AnimationsActivity.class);
        addMenuItem("Adapter", AdapterActivity.class);
        addMenuItem("Floating Action Button", FabActivity.class);
        addMenuItem("Custom Floating Action Button", CustomFabActivity.class);
        addMenuItem("Simple Paper Transformations", SimplePaperTransformations.class);
        addMenuItem("RecyclerView Animation", RecyclerViewAnimationActivity.class);
        addMenuItem("Dynamic Surfaces", DynamicSurfacesActivity.class);
        addMenuItem("Layout Animation", LayoutAnimationActivity.class);
        addMenuItem("Palette", PaletteActivity.class);
        addMenuItem("Font", FontActivity.class);
        addMenuItem("Immersive Images", ImmersiveImagesActivity.class);
        addMenuItem("Rounded Images", RoundedImagesActivity.class);
        addMenuItem("ThreeTwo ImageView", ThreeTwoImageActivity.class);
        addMenuItem("Animate", AnimateActivity.class);
        addMenuItem("Transition", GridActivity.class);
        addMenuItem("Interpolator", InterpolatorActivity.class);
        addMenuItem("Coordinate", CoordinateActivity.class);
        addMenuItem("Vector Drawabls", VectorDrawableActivity.class);
        addMenuItem("Property Animation", PropertyAnimationActivity.class);
        addMenuItem("Invoke Star", InvokeStarDetailActivity.class);
        addMenuItem("Memory Management", MemoryManagement.class);
        addMenuItem("Activity Lifecycle", LifecycleActivity.class);
        addMenuItem("Data Binding", DataBindingActivity.class);
        addMenuItem("Wrapper Property", WrapperActivity.class);
        addMenuItem("Bitmap Scaling", BitmapScaling.class);
        addMenuItem("Recycler View", RecyclerActivity.class);
        addMenuItem("Lifecycle of Fragment inside ViewPager", FragmentLifecycleInsideViewPager.class);
        addMenuItem("Window", UseWindow.class);
        addMenuItem("Change Orientation", ChangeOrientation.class);
        addMenuItem("Get Dimension", TestGetDimens.class);
        addMenuItem("Restore State", RestoreActivityState.class);
        addMenuItem("Show Dialog", ShowDialog.class);
        addMenuItem("GridLayout", GridLayoutActivity.class);
        addMenuItem("ViewPager List", ViewPagerListActivity.class);
        addMenuItem("OpenGL ES", OpenGLES20Activity.class);
        addMenuItem("Hello World", HelloWorldActivity.class);
        addMenuItem("Launch Mode", AlphaActivity.class);
        addMenuItem("Intent", IntentActivity.class);
        addMenuItem("Thread", ThreadActivity.class);
        addMenuItem("Custom ViewGroup", CustomViewGroupActivity.class);
        addMenuItem("Hide Keyboard", HideKeyboardActivity.class);
        addMenuItem("Basic UI", BasicUiActivity.class);
        addMenuItem("Use Android Library", UseAndroidLibraryActivity.class);
        addMenuItem("Coordinate Threads", LoggerActivity.class);
        addMenuItem("Simple Service", SaveMyLocationActivity.class);
        addMenuItem("List Video", VideoActivity.class);
        addMenuItem("EventBus", BusActivity.class);
        addMenuItem("Volley Test", VolleyTestActivity.class);
        addMenuItem("State List Drawable", StateListActivity.class);
    }

}
