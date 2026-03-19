package com.njau.mapper;

import com.njau.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;
@Mapper
public interface  DetectionMapper {
    List<DropInfo> selectDropInfo(String date);

    List<SoundInfo> selectSoundInfo(String date);

    InputInfo selectInputInfo();

    ScoreInput select_all();

    int insertDropResult(DropInfo dropInfo);

    int insertSoundResult(SoundInfo soundInfo);

    int insertInputInfo(InputInfo inputInfo);

    int insertDeadweight_loss_rateInfo(Deadweight_loss_rate deadweight_loss_rate);

    int insertQuantity_of_food_intake(Quantity_of_food_intake quantity_of_food_intake);

    int insertWater_intakeInfo(Water_intake water_intake);

    int insertDeformed_eggsInfo(Deformed_eggs deformed_eggs);

    int insertWeight(Weight weight);

    int insertUniformity(Uniformity uniformity);

    int insertAverage_egg(Average_egg average_egg);

    int insertEnvironmental_T(Environmental_T environmental_t);

    int insertEnvironmental_H(Environmental_H environmental_h);

    int insertEnvironmental_CO2(Environmental_CO2 environmental_co2);

    int insertEnvironmental_NH3(Environmental_NH3 environmental_nh3);

    int insert_eggInfo(EggInfo eggInfo);

    int insert_Score(Score score);

    List<CagePhotoInfo> selectCagePhoto(@Param("date") String date);

    int claimCagePhotos(@Param("date") String date, @Param("limit") int limit);

    int markCagePhotoDone(@Param("id") BigInteger id);

    int markCagePhotoFailed(@Param("id") BigInteger id);

    int insertClusterInfo(ClusterInfo clusterInfo);

    ClusterInfo selectClusterInfo(@Param("date")String date, @Param("filename")String filename);

    int insertEggsInfo(EggsInfo eggInfo);

    ClusterInfo selectEggsInfo(@Param("date")String date, @Param("filename")String filename);

    int insertCombInfo(CombInfo combInfo);

    CombInfo selectCombInfo(@Param("date")String date, @Param("filename")String filename);

    int insertOmInfo(OmInfo omInfo);

    OmInfo selectOmInfo(@Param("date")String date, @Param("filename")String filename);

    Auto_info_xjr selectAuto_info_xjr();

    int insertAuto_info_xjr(Auto_info_xjr auto_info);

    void insert_score_new(Score_New score_new);
}
