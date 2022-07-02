package com.huang.mphealth.controller;//package com.huang.controller;
//
//import javax.annotation.Resource;
//
//import com.huang.model.Block;
//import com.huang.model.Transaction;
//import com.huang.service.BlockService;
//import com.huang.utlis.BlockCache;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//
//
//
//import java.util.List;
//
//@Controller
//public class BlockController {
//
//	@Resource
//	BlockService blockService;
//
//
//
//	@Autowired
//	BlockCache blockCache;
//
//	/**
//	 * 查看当前节点区块链数据
//	 * @return
//	 */
//	@GetMapping("/scan")
//	@ResponseBody
//	public String scanBlock() {
//		return JSON.toJSONString(blockCache.getBlockChain());
//	}
//
//	/**
//	 * 查看当前节点区块链数据
//	 * @return
//	 */
//	@GetMapping("/data")
//	@ResponseBody
//	public String scanData() {
//		List<Transaction> packedTransactions = blockCache.getPackedTransactions();
//
//		return JSON.toJSONString(blockCache.getPackedTransactions());
//	}
//
//	/**
//	 * 创建创世区块
//	 * @return
//	 */
//	@GetMapping("/create")
//	@ResponseBody
//	public String createFirstBlock() {
//		String genesisBlock = blockService.createGenesisBlock();
//		System.out.println(genesisBlock);
//
//		List<Block> blockChain = blockCache.getBlockChain();
//
//		return JSON.toJSONString(blockCache.getBlockChain());
//	}
//
//}
