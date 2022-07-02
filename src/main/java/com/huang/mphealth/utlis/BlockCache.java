//package com.huang.mphealth.utlis;
//
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//
//import com.huang.model.Block;
//import com.huang.model.Transaction;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BlockCache {
//
//	/**
//	 * 当前节点的区块链结构
//	 */
//	private List<Block> blockChain = new CopyOnWriteArrayList<Block>();
//
//	/**
//	 * 已打包保存的业务数据集合
//	 */
//	private List<Transaction> packedTransactions = new CopyOnWriteArrayList<Transaction>();
//
//
//
//	/**
//	 * 获取最新的区块，即当前链上最后一个区块
//	 *
//	 * @return
//	 */
//	public Block getLatestBlock() {
//		return blockChain.size() > 0 ? blockChain.get(blockChain.size() - 1) : null;
//	}
//
//	public List<Block> getBlockChain() {
//		return blockChain;
//	}
//
//	public void setBlockChain(List<Block> blockChain) {
//		this.blockChain = blockChain;
//	}
//
//	public List<Transaction> getPackedTransactions() {
//		return packedTransactions;
//	}
//
//	public void setPackedTransactions(List<Transaction> packedTransactions) {
//		this.packedTransactions = packedTransactions;
//	}
//
//
//
//}
