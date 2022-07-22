package com.adolesce.server.date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskScheduleModel extends Model<TaskScheduleModel> {

    /**秒  */
    Integer second;

    /**分  */
    Integer minute;

    /**时  */
    Integer hour;

}