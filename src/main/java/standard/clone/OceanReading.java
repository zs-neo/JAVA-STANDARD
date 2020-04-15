package standard.clone;

public class OceanReading implements Cloneable {
  
  private DepthReading depthReading;
  private TemperatureReading temperatureReading;
  
  public OceanReading(double depth, double temp) {
    this.depthReading = new DepthReading(depth);
    this.temperatureReading = new TemperatureReading(temp);
  }
  
  @Override
  public Object clone() {
    OceanReading obj = null;
    try {
      obj = (OceanReading) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    obj.depthReading = (DepthReading) obj.depthReading.clone();
    obj.temperatureReading = (TemperatureReading) obj.temperatureReading.clone();
    return obj;
  }
}
