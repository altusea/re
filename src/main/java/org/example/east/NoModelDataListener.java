package org.example.east;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.util.StringUtils;

import java.util.List;
import java.util.Map;

public class NoModelDataListener extends AnalysisEventListener<Map<Integer, String>> {

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
                    String.format("('%s', '%s', '%s', '%s', '%s', %s, %s, %d)",
                            currentMouldId,
                            data.get(7),
                            data.get(8),
                            data.get(9),
                            data.get(10),
                            StringUtils.isBlank(data.get(11)) ? "null" : ("是".equals(data.get(11)) ? "1" : "0"),
                            StringUtils.isBlank(data.get(12)) ? "null" : ("是".equals(data.get(12)) ? "1" : "0"),
                            order++)

            );
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

        String sr = "insert into east_jt.fill_md_details( md_id, md_data_name, md_data_code,  md_data_element_code, md_data_explain, md_required, md_primary_key_flag, order) values \n";
        System.out.println(sr + String.join(", \n", cachedDataList) + ";\n");
        cachedDataList.clear();
    }
}
