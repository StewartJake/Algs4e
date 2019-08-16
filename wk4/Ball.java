public class Ball
{
    private double x, y;        // position
    private double vx, vy;      // velocity
    private final double radius;
    
    public Ball()
    { }

    public void move(double dt)
    {
        if ((x +vx*dt < radius) || (x + vx*dt > 1.0 - radius))
            vx = -vx;
        if ((y +vy*dt < radius) || (y + vy*dt > 1.0 - radius))
            vy = -vy;
        rx = rx + vx*dt;
        ry = ry + vy*dt;
    }

    public void draw()
    {   StdDraw.filledCircle(rx, ry, radius);   }
}
