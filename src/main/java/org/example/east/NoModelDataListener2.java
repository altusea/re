package org.example.east;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.util.StringUtils;

import java.util.List;
import java.util.Map;

public class NoModelDataListener2 extends AnalysisEventListener<Map<Integer, String>> {

    private String currentMouldId = "init";

    int order = 0;

    private final List<String> cachedDataList = ListUtils.newArrayList();

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        if (StringUtils.isNotBlank(data.get(0))) {
            return;
        }

        if (StringUtils.isBlank(data.get(4))) {
            cachedDataList.add(
                    String.format("update east_jt.fill_md_details set order = %d where md_id = '%s' and md_data_code = '%s'",
                            order++,
                            currentMouldId,
                            data.get(8)));
        } else {
            currentMouldId = data.get(4);
            order = 0;
            saveData();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        System.out.println(String.join(";\n", cachedDataList) + "\n");
        cachedDataList.clear();
    }
}
