package com.dfsek.scatmansworld;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Random;

public class ScatmanChunkGenerator extends ChunkGenerator {
    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);
        SimplexOctaveGenerator noise = new SimplexOctaveGenerator(world.getSeed(), 4);
        SimplexOctaveGenerator canyonNoise = new SimplexOctaveGenerator(world.getSeed(), 4);
        noise.setScale(0.0125);
        canyonNoise.setScale(0.0075);
        for(int x = 0; x < 16; x++) {
            for(int z = 0; z < 16; z++) {
                int h = (int) (((noise.noise(chunkX*16 + x, chunkZ * 16 + z, 0.5D, 0.5D, true)
                        + noise.noise((chunkX*16 + x)*2, (chunkZ * 16 + z)*2, 0.5D, 0.5D, true)*0.5
                        + noise.noise((chunkX*16 + x)*4, (chunkZ * 16 + z)*4, 0.5D, 0.5D, true)*0.25
                        + noise.noise((chunkX*16 + x)*8, (chunkZ * 16 + z)*8, 0.5D, 0.5D, true)*0.125) + 1) * 15 + 54);
                double absNoise = Math.abs(canyonNoise.noise(chunkX*16 + x, chunkZ * 16 + z, 0.5D, 0.5D, true));
                if(absNoise < 0.04) h = (h-54)/5 + 34;
                for(int y = 0; y < h; y++) {
                    if(y > h-3) chunk.setBlock(x, y, z, Material.RED_SAND);
                    else if(y > h-6 || (absNoise < 0.06 && absNoise > 0.04)) chunk.setBlock(x, y, z, Material.ORANGE_TERRACOTTA);
                    else chunk.setBlock(x, y, z, Material.STONE);
                }
                if(random.nextInt(100) < 50) chunk.setBlock(x,1,z,Material.BEDROCK);
                if(random.nextInt(100) < 40) chunk.setBlock(x,2,z,Material.BEDROCK);
                if(random.nextInt(100) < 30) chunk.setBlock(x,3,z,Material.BEDROCK);
                if(random.nextInt(100) < 20) chunk.setBlock(x,4,z,Material.BEDROCK);
                if(random.nextInt(100) < 10) chunk.setBlock(x,5,z,Material.BEDROCK);
            }
        }
        chunk.setRegion(0, 0, 0, 16, 1, 16, Material.BEDROCK);
        return chunk;
    }
}
