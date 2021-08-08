package xyz.logicaldevz.Rating;

import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.*;
import android.graphics.drawable.LayerDrawable;
import android.content.Context;
import android.widget.RatingBar;
import android.widget.LinearLayout;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.appinventor.components.common.*;
@DesignerComponent(
        version = 1,
        description = "",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "")

@SimpleObject(external = true)
public class Rating extends AndroidNonvisibleComponent {
  private Context context;
  private RatingBar ratingBar;
  private int progressColor;
  public Rating(ComponentContainer container) {
    super(container.$form());
    context = container.$context();
    ratingBar = new RatingBar(context);
  }
  @SimpleProperty(description = "To set if rating bar is indicator or not")
  public void SetIndicator(boolean isIndicator) {
    ratingBar.setIsIndicator(isIndicator); // It means user can't set rating bar value
  }
  @SimpleProperty(description = "To set rating bar value")
  public void SetRating(float rating) {
    ratingBar.setRating(rating);
  }
  @SimpleProperty(description = "To set the step size of the rating bar")
  public void SetStepSize(float stepSize) {
    ratingBar.setStepSize(stepSize);
  }
  @SimpleProperty(description = "Get number of stars")
  public int getNumStars(){
    return ratingBar.getNumStars();
  }
  @SimpleProperty(description = "Get rating bar value")
  public float getRating(){
    return ratingBar.getRating();
  }
  @SimpleProperty(description = "Get step size of the rating bar")
  public float getStepSize(){
    return ratingBar.getStepSize();
  }
  @SimpleProperty(description = "Set the upper range of the progress bar max value")
  public void SetMax(int max){
    ratingBar.setMax(max);
  }

  @SimpleProperty(description = "To set the color of progress of rating bar")
  public void SetProgressColor(int color) {
    progressColor = color;
    try{
    LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
    if (stars != null) {
    stars.getDrawable(2).setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN);
    }
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  @SimpleEvent
  public void OnRatingChanged(float rating , boolean fromUser) {
    EventDispatcher.dispatchEvent(this, "OnRatingChanged", rating, fromUser);
  }
  @SimpleProperty
  public boolean IsIndicator(){
      return ratingBar.isIndicator();
  }
  @SimpleFunction(description = "Create rating bar programmatically")
  public void CreateRatingBar(AndroidViewComponent view, int numStars ) {
    LinearLayout linearLayout = new LinearLayout(context);

    ratingBar.setNumStars(numStars);
    linearLayout.addView(ratingBar);
    ViewGroup viewGroup = (ViewGroup) view.getView();
    viewGroup.addView(linearLayout);
    ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
      @Override
      public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        OnRatingChanged(rating, fromUser);
      }
      });
  }
  
}
