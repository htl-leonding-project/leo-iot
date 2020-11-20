from matplotlib import pyplot as plt
import numpy as np
import seaborn as sns; sns.set()

def plot_metrics(histories):
    prop_cycle = plt.rcParams['axes.prop_cycle']
    COLOR_CYCLE = prop_cycle.by_key()['color']
    
    color_dict = {name: COLOR_CYCLE[i % len(COLOR_CYCLE)] for i, name in enumerate(histories)}
    
    metrics =  ['binary_crossentropy', 'auc', 'precision', 'recall']
    f, axarr = plt.subplots(2, 2, figsize=(20, 20))
    for n, (metric, ax) in enumerate(zip(metrics, axarr.flat)):
        label = metric.replace("_"," ").capitalize()
        for name, history in histories.items():
            ax.plot(history.epoch,  history.history[metric], color=color_dict[name], label=name + ' Train')
            ax.plot(history.epoch, history.history['val_'+metric],
                 color=color_dict[name], linestyle="--", label=name + ' Val')
        ax.set_title(label)
        ax.set(xlabel='Epoch', ylabel=label)
        ax.set_ylim([0,1])

    plt.legend()

def plot_confusion_matrix(eval_results):
    con_mat = np.array([
        eval_results['tn'],
        eval_results['fp'],
        eval_results['fn'],
        eval_results['tp']
    ]).reshape((2,2))
    #con_mat_norm = np.around(con_mat.astype('float') / con_mat.sum(axis=1)[:, np.newaxis], decimals=2)
    
    figure = plt.figure(figsize=(8,8))
    sns.heatmap(con_mat, annot=True, cmap=plt.cm.Blues)
    plt.tight_layout()
    plt.ylabel('True label')
    plt.xlabel('Predicted label')
    plt.show()
    