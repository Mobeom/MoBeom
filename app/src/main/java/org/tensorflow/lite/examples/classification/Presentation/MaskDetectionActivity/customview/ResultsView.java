//@copyright for 박성진

package org.tensorflow.lite.examples.classification.Presentation.MaskDetectionActivity.customview;

import java.util.List;
import org.tensorflow.lite.examples.classification.Presentation.MaskDetectionActivity.tflite.Classifier.Recognition;

public interface ResultsView {
  public void setResults(final List<Recognition> results);
}
