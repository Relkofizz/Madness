package relkofizz.madness.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import relkofizz.madness.blocks.madTable.MadTableTile;

public class PacketUpdateMadTable implements IMessage {
	
	private BlockPos pos;
	private ItemStack stack;
	private long lastChangeTime;
	
	public PacketUpdateMadTable(BlockPos pos, ItemStack stack){
		this.pos =pos;
		this.stack = stack;
	}
	
	public PacketUpdateMadTable(MadTableTile te){
		this(te.getPos(), te.inventory.getStackInSlot(0));
	}

	public PacketUpdateMadTable(){
	}
	

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
		ByteBufUtils.writeItemStack(buf, stack);
		buf.writeLong(lastChangeTime);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		pos = BlockPos.fromLong(buf.readLong());
		stack = ByteBufUtils.readItemStack(buf);
		lastChangeTime = buf.readLong();
	}
	
	public static class Handler implements IMessageHandler<PacketUpdateMadTable, IMessage> {

		@Override
		public IMessage onMessage(PacketUpdateMadTable message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				MadTableTile te = (MadTableTile)Minecraft.getMinecraft().world.getTileEntity(message.pos);
				te.inventory.setStackInSlot(0, message.stack);
			});
			return null;
		}
	}
}
