package net.artyrian.frontiers.entity.ai.crawler;

import net.artyrian.frontiers.entity.mob.CrawlerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class CrawlerIgniteGoal extends Goal
{
    private final CrawlerEntity crawler;
    @Nullable
    private LivingEntity target;

    public CrawlerIgniteGoal(CrawlerEntity crawler)
    {
        this.crawler = crawler;
        this.setControls(EnumSet.of(Control.TARGET));
    }

    @Override
    public boolean canStart()
    {
        LivingEntity livingEntity = this.crawler.getTarget();
        return this.crawler.getFuseSpeed() > 0 || livingEntity != null && this.crawler.squaredDistanceTo(livingEntity) < 9.0;
    }

    @Override
    public void start()
    {
        this.target = this.crawler.getTarget();
        if (this.target != null) this.crawler.getNavigation().startMovingTo(this.target, 0.6);
    }

    @Override
    public void stop() {
        this.target = null;
    }

    @Override
    public boolean shouldRunEveryTick()
    {
        return true;
    }

    @Override
    public void tick()
    {
        if (this.target == null)
        {
            this.crawler.setFuseSpeed(-1);
        }
        else if (this.crawler.squaredDistanceTo(this.target) > 49.0)
        {
            this.crawler.setFuseSpeed(-1);
        }
        else if (!this.crawler.getVisibilityCache().canSee(this.target))
        {
            this.crawler.setFuseSpeed(-1);
        }
        else
        {
            this.crawler.setFuseSpeed(1);
        }
    }
}