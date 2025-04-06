<!-- name-start -->
# ResourceTrimmer [![CurseForge Project](https://img.shields.io/curseforge/dt/1235231?logo=curseforge&label=CurseForge&style=flat-square&labelColor=2D2D2D&color=555555)](https://www.curseforge.com/minecraft/mc-mods/resource-trimmer) [![Modrinth Project](https://img.shields.io/modrinth/dt/TVKrCZNh?logo=modrinth&label=Modrinth&style=flat-square&labelColor=2D2D2D&color=555555)](https://modrinth.com/mod/resource-trimmer) [![Maven Project](https://img.shields.io/maven-metadata/v?style=flat-square&logoColor=D31A38&labelColor=2D2D2D&color=555555&label=Latest&logo=gradle&metadataUrl=https%3A%2F%2Fmaven.blamejared.com%2Fnet%2Fdarkhax%2Fresourcetrimmer%2Fresourcetrimmer-common-1.21.1%2Fmaven-metadata.xml)](https://maven.blamejared.com/net/darkhax/resourcetrimmer)
<!-- name-end -->
<!-- description-start -->
Reduces the size of resource locations on the network. The documentation for this mod can be found [here](https://docs.darkhax.net/mods/resourcetrimmer).
<!-- description-end -->

## What does this do?
This mod changes how Minecraft sends IDs across the network. If the ID is in the `minecraft` namespace the namespace will not be serialized, saving 10 bytes per ID. This reduces the size of packets by quite a bit and can potentially improve login times and server performance.

## How It Works
Minecraft uses namespaced identifiers (often called resource locations) for most game objects. Each identifier follows the format of `namespace:path` where the namespace is the owner, and the path is the name of the game object. If the namespace is not specified it is assumed to be `minecraft`, the built-in default. The vanilla networking code always includes the namespace and the separator even when it is unnecessary. This mod simply checks if the namespace is `minecraft` and skips sending it. This reduces the size by 10 bytes per occurrence.

## Is this mod server side or client side?
This mod is completely optional on both sides. If a server has the mod installed all clients will receive the smaller packets even if they do not have the mod installed. If the client has the mod installed packets they send to the server will also be smaller even if the server does not have the mod.

## How effective is this mod?
Each occurrence of a built-in ID will be 10 bytes smaller. Players can expect the login process to be at least 105kb smaller. This number increases with the amount of additional content installed, for example the ATM 10 mod pack login process was 125kb smaller. While this is a small reduction, it can still have a meaningful impact for users with a poor internet connection and for servers with a large player base. This can also help mitigate issues with individual packets exceeding the 2mb limit. It is not a magic bullet that will solve all of your networking issues, just another potential optimization tool to consider.

The following table shows how long it would take to send a 120kb packet over a perfect bidirectional network. In the real world speeds are limited by the upload speed of the server, the download speed of the client, and many other factors.

| Connection (Mbps) | Time (ms) |
|-------------------|-----------|
| 5 Mbps            | 192 ms    |
| 10 Mbps           | 96 ms     |
| 15 Mbps           | 64 ms     |
| 100 Mbps          | 9.6 ms    |
| 200 Mbps          | 4.8 ms    |
| 500 Mbps          | 1.92 ms   |
| 1000 Mbps         | 0.96 ms   |

**Note:** 120kb is 960,000 bits and a 1Mbps connection does 1,000,000 bits per second. Internet providers list internet speeds in megabits and not megabytes to mislead consumers.

## Compatibility & Other Concerns
This mod uses Mixin to apply its changes. While this is common place in modern Minecraft it always carries a risk of causing incompatibilities with other mods or modified clients. The changes made by this mod are within the vanilla spec and should not cause any issues with clients that fully implement it. There are no known incompatibilities.

<!-- maven-start -->
## Maven Dependency

If you are using [Gradle](https://gradle.org) to manage your dependencies, add the following into your `build.gradle` file. Make sure to replace the version with the correct one. All versions can be viewed [here](https://maven.blamejared.com/net/darkhax/resourcetrimmer).

```gradle
repositories {
    maven { 
        url 'https://maven.blamejared.com'
    }
}

dependencies {
    // NeoForge
    implementation group: 'net.darkhax.resourcetrimmer', name: 'resourcetrimmer-neoforge-1.21.1', version: '21.1.0'

    // Forge
    implementation group: 'net.darkhax.resourcetrimmer', name: 'resourcetrimmer-forge-1.21.1', version: '21.1.0'

    // Fabric & Quilt
    modImplementation group: 'net.darkhax.resourcetrimmer', name: 'resourcetrimmer-fabric-1.21.1', version: '21.1.0'

    // Common / MultiLoader / Vanilla
    compileOnly group: 'net.darkhax.resourcetrimmer', name: 'resourcetrimmer-common-1.21.1', version: '21.1.0'
}
```
<!-- maven-end -->

<!-- sponsor-start -->
## Sponsors

[![](https://assets.blamejared.com/nodecraft/darkhax.jpg)](https://nodecraft.com/r/darkhax)    
ResourceTrimmer is sponsored by Nodecraft. Use code **[DARKHAX](https://nodecraft.com/r/darkhax)** for 30% of your first month of service!
<!-- sponsor-end -->