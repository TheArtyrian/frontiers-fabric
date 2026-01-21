package net.vertisoft.vectorlib.agnostic.util;

/** A cross-platform class used to fill specific registries in post, I.E composting & furnace fuels */
public class VectorPropertyBaker
{
    private final Flammable FLAMMABLE = new Flammable();
    private final Compostable COMPOSTABLE = new Compostable();
    private final FurnaceFuel FUEL = new FurnaceFuel();

    public VectorPropertyBaker()
    {

    }

    public void bake()
    {
        FLAMMABLE.bake();
        COMPOSTABLE.bake();
        FUEL.bake();
    }

    private static class Flammable
    {
        private void bake()
        {

        }
    }

    private static class Compostable
    {
        private void bake()
        {

        }
    }

    private static class FurnaceFuel
    {
        private void bake()
        {

        }
    }
}