package com.celeste.internal.registry;

import com.celeste.internal.model.Packet;
import com.celeste.internal.model.PacketContent;
import com.celeste.internal.model.protocol.type.ConnectionState;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;

@Getter
public final class ProtocolRegistry {

  public static final ProtocolRegistry INSTANCE = new ProtocolRegistry();

  private final Map<Class<? extends PacketContent>, Packet> statusPackets;
  private final Map<Class<? extends PacketContent>, Packet> loginPackets;
  private final Map<Class<? extends PacketContent>, Packet> playPackets;

  public ProtocolRegistry() {
    this.statusPackets = new ConcurrentHashMap<>();
    this.loginPackets = new ConcurrentHashMap<>();
    this.playPackets = new ConcurrentHashMap<>();
  }

  private void register(final Packet<? extends PacketContent> packet, final ConnectionState state) {
    switch (state) {
      case STATUS: statusPackets.put(packet.getType(), packet);
      case LOGIN: loginPackets.put(packet.getType(), packet);
      case PLAY: playPackets.put(packet.getType(), packet);
    }
  }

}
