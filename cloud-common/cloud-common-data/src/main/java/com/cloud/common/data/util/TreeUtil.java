package com.cloud.common.data.util;


import com.cloud.common.entity.TreeEntity;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成树
 * @author Aijm
 * @since 2019/11/9
 */
@UtilityClass
public class TreeUtil {


    /**
     * 树 根据父子关系排序  该sourcelist 已经按照 sort 排序
     * @param list 排序后输出的存储对象
     * @param sourcelist 需要处理的数据
     * @param parentId  一般为0 第一个节点的parentId (也表示 id为parentId的数据不会显示)
     * @param cascade  一般为true
     */
    public <T extends TreeEntity> void sortList(List<T> list, List<T> sourcelist, Long parentId, boolean cascade){
        int size = sourcelist.size();
        for (int i=0; i<size; i++){
            T e = sourcelist.get(i);
            if (parentId.equals(e.getParentId())){
                list.add(e);
                if (cascade){
                    // 判断是否还有子节点, 有则继续获取子节点
                    for (int j=0; j<size; j++){
                        T child = sourcelist.get(j);
                        if (child.getParentId().equals(e.getId())){
                            sortList(list, sourcelist, e.getId(), true);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
	 * 两层循环实现建树
	 *
	 * @param treeNodes 传入的树节点列表
	 * @return
	 */
	public <T extends TreeEntity> List<T> build(List<T> treeNodes, Object root) {

		List<T> trees = new ArrayList<>();
		for (T treeNode : treeNodes) {
			if (root.equals(treeNode.getParentId())) {
				trees.add(treeNode);
			}
			for (T it : treeNodes) {
				if (it.getParentId().equals(treeNode.getId())) {
					if (treeNode.getChildrens() == null) {
						treeNode.setChildrens(new ArrayList<>());
					}
					treeNode.getChildrens().add(it);
				}
			}
		}
		return trees;
	}

	/**
	 * 使用递归方法建树
	 * @param treeNodes
	 * @param root
	 * @param <T>
	 * @return
	 */
	public <T extends TreeEntity> List<T> buildTree(List<T> treeNodes, Object root) {
		List<T> trees = new ArrayList<T>();
		for (T treeNode : treeNodes) {
			if (root.equals(treeNode.getParentId())) {
				trees.add(findChildren(treeNode, treeNodes));
			}
		}
		return trees;
	}

	/**
	 * 递归查找子节点
	 * @param treeNode
	 * @param treeNodes
	 * @param <T>
	 * @return
	 */
	public <T extends TreeEntity> T findChildren(T treeNode, List<T> treeNodes) {
		for (T it : treeNodes) {
			if (treeNode.getId().equals(it.getParentId())) {
				if (treeNode.getChildrens() == null) {
					treeNode.setChildrens(new ArrayList<>());
				}
				treeNode.getChildrens().add(findChildren(it, treeNodes));
			}
		}
		return treeNode;
	}


}
